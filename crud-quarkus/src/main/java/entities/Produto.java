package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Produto extends PanacheEntity {

    public String nome;

    public BigDecimal valor;

    @CreationTimestamp
    public LocalDateTime dataCriacao;

    @UpdateTimestamp
    public LocalDateTime dataAtualizacao;

    @Deprecated
    public Produto(){}

    public Produto(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
