package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.FaixaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaixaService {

    private final FaixaRepository repository;

    public List<Faixa> buscarPorTitulo(String titulo){
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Faixa> buscarDetalhado(String titulo) {
        return repository.buscarDetalhadoPorTitulo(titulo);
    }
}
