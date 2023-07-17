package dtos;

import java.math.BigDecimal;

public record CadastrarProdutoDTO(
        String nome,
        BigDecimal valor
) {
}
