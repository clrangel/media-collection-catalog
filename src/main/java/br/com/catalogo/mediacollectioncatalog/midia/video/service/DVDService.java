package br.com.catalogo.mediacollectioncatalog.midia.video.service;


import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.repository.DiretorRepository;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.DVD;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.mapstruct.DVDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.video.repository.DVDRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DVDService {

    private final DVDRepository repository;
    private final DiretorRepository diretorRepository;
    private final DVDMapper mapper;

    @Transactional
    public DVDResponseDTO cadastrarDVD(DVDRequestDTO dto){

        // 1. Converter DTO → Entity
        // O MapStruct converte automaticamente os campos simples
        DVD dvd = mapper.toEntity(dto);

        // 2. Buscar todos os diretores pelos IDs enviados no DTO
        // O método findAllById retorna uma lista de diretores encontrados
        List<Diretor> diretores = diretorRepository.findAllById(dto.diretoresIds());

        // 3. Validar se todos os diretores existem
        // Se a quantidade encontrada for diferente da enviada,
        // significa que algum ID não existe no banco
        if (diretores.size() != dto.diretoresIds().size()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Um ou mais diretores não foram encontrados"
            );
        }

        // 4. Associar os diretores ao DVD
        // Como o relacionamento é ManyToMany,
        // basta adicionar a lista de diretores na entidade
        dvd.setDiretores(diretores);

        // 5. Salvar o DVD no banco
        // O Hibernate persiste a entidade e a tabela de relacionamento
        DVD dvdSalvo = repository.save(dvd);

        // 6. Converter Entity → ResponseDTO
        // O mapper transforma a entidade no retorno da API
        return mapper.toDTO(dvdSalvo);
    }

    @Transactional
    public DVDResponseDTO atualizarDVD(Long id, DVDRequestDTO dto){

        // 1. Busca o DVD existente
        DVD dvd = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "DVD não encontrado com o ID: " + id
                ));

        // 2. Atualiza os campos simples
        // titulo, sinopse, genero, etc.
        mapper.updateFromDto(dto, dvd);

        // 3. Atualiza os diretores manualmente
        if (dto.diretoresIds() != null) {

            // Busca todos os diretores enviados
            List<Diretor> diretores = diretorRepository.findAllById(dto.diretoresIds());

            // Valida se todos existem
            if (diretores.size() != dto.diretoresIds().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Um ou mais diretores não foram encontrados");
            }

            // Substitui a lista antiga
            dvd.setDiretores(diretores);
        }

        // 4. Salva atualização
        DVD dvdAtualizado = repository.save(dvd);

        // 5. Retorna DTO
        return mapper.toDTO(dvdAtualizado);
    }


    @Transactional
    public void deletarDVD(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DVD não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }
}
