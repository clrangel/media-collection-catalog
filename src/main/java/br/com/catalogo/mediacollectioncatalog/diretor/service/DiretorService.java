package br.com.catalogo.mediacollectioncatalog.diretor.service;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorRequestDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorResponseDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.mapper.DiretorMapper;
import br.com.catalogo.mediacollectioncatalog.diretor.repository.DiretorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
