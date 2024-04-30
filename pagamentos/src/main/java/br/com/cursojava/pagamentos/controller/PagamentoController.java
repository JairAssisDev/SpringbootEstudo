package br.com.cursojava.pagamentos.controller;

import br.com.cursojava.pagamentos.dto.PagamentoDto;
import br.com.cursojava.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    public List<PagamentoDto> listar(){
        return pagamentoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> buscaPorID(@PathVariable @NotNull Long id){
        PagamentoDto dto = pagamentoService.getById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto,
                                                  UriComponentsBuilder uriBuilder){
            PagamentoDto pagamento = pagamentoService.CreatePayment(dto);
            var uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

            return ResponseEntity.created(uri).body(pagamento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizarRegistro(@PathVariable @NotNull Long id ,
                                                          @RequestBody @Valid PagamentoDto dto){

        PagamentoDto pagamentoAtualizado = pagamentoService.UpdatePayment(id,dto);
        return ResponseEntity.ok(pagamentoAtualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> deletarRegistro(@PathVariable @NotNull Long id){
        pagamentoService.DeletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
