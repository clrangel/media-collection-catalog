package br.com.catalogo.mediacollectioncatalog.diretor.service;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorRequestDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorResponseDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.mapper.DiretorMapper;
import br.com.catalogo.mediacollectioncatalog.diretor.repository.DiretorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class DiretorService {

    private final DiretorRepository repository;

    @Transactional
    public DiretorResponseDTO cadastrarDiretor(DiretorRequestDTO dto){

        Diretor diretor = DiretorMapper.toEntity(dto);

        Diretor diretorSalvo = repository.save(diretor);

        return DiretorMapper.toDTO(diretorSalvo);
    }

    @Transactional
    public DiretorResponseDTO atualizar(Long id, DiretorRequestDTO dto) {

        Diretor diretor = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Diretor não encontrado com ID: " + id
                ));

        // Atualiza os dados
        DiretorMapper.updateFromDTO(dto, diretor);

        // Salva alterações
        Diretor atualizado = repository.save(diretor);

        return DiretorMapper.toDTO(atualizado);
    }

    @Transactional
    public void deletar(Long id) {

        Diretor diretor = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Diretor não encontrado com ID: " + id
                ));

        if (!diretor.getMidiasVideos().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível excluir diretor com mídias associadas"
            );
        }

        repository.delete(diretor);
    }

    public DiretorResponseDTO buscarPorId(Long id) {

        Diretor diretor = repository.buscarComMidias(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Diretor não encontrado com ID: " + id
                ));

        return DiretorMapper.toDTO(diretor);
    }
}
