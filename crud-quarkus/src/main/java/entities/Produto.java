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
}
