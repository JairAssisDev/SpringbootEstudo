package br.com.cursojava.pagamentos.service;

import br.com.cursojava.pagamentos.dto.PagamentoDto;
import br.com.cursojava.pagamentos.model.Pagamento;
import br.com.cursojava.pagamentos.model.Status;
import br.com.cursojava.pagamentos.repository.PagamentosRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentosRepository repository;


    private final ModelMapper modelMapper;


    public List<PagamentoDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(p -> modelMapper.map(p, PagamentoDto.class))
                .collect(Collectors.toList());
    }

    public PagamentoDto getById(Long id) {
    Optional<Pagamento> Optionalpagamento = repository.findById(id);
    Pagamento pagamento = Optionalpagamento.orElseThrow(EntityNotFoundException::new);
    return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto CreatePayment(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto UpdatePayment(Long id,PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento=repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);

    }

    public void DeletePayment(Long id) {
        repository.deleteById(id);
    }
}
