package resources;

import dtos.CadastrarProdutoDTO;
import entities.Produto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void salvarProduto(CadastrarProdutoDTO dto) {
        new Produto(dto.nome(), dto.valor()).persist();
    }

    @PUT
    @Path(("{id}"))
    @Transactional
    public Produto salvarProdutoPorID(@PathParam("id") Long id, @RequestBody CadastrarProdutoDTO dto) {
        Optional<Produto> produto = Produto.findByIdOptional(id);
        if (produto.isEmpty()) throw new Error("Produto não encontrado");
        produto.get().setNome(dto.nome());
        produto.get().setValor(dto.valor());
        produto.get().persist();
        return produto.get();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarProduto(@PathParam("id") Long id) {
        Optional<Produto> produto = Produto.findByIdOptional(id);
        if (produto.isEmpty()) throw new Error("Produto não encontrado");
        produto.get().delete();
    }
}
