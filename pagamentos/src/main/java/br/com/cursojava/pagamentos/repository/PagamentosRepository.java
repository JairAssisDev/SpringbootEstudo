package br.com.cursojava.pagamentos.repository;

import br.com.cursojava.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentosRepository extends JpaRepository<Pagamento, Long> {


}
