package br.com.r3connect.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.r3connect.model.Produto;
import br.com.r3connect.repository.CategoriaRepository;
import br.com.r3connect.repository.ProdutoRepository;

@RestController
@RequestMapping("/Produtos")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable @Valid Long id){
	return produtoRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable @Valid String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	// Consulta por preço entre dois valores (Between com Native Query)

    @GetMapping("/preco_inicial/{menor}/preco_final/{maior}")
    public ResponseEntity<List<Produto>> getByPrecoEntreNatve(@PathVariable BigDecimal menor, @PathVariable BigDecimal maior){
        return ResponseEntity.ok(produtoRepository.buscarProdutosEntre(menor, maior));

    }
    
    @PostMapping
    public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto) {
    	return categoriaRepository.findById(produto.getCategoria().getId())
    			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
    			.orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping
    public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto) {
    	if(produtoRepository.existsById(produto.getId())) {
    		if(categoriaRepository.existsById(produto.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
    		} else {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    		}
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto (@Valid @PathVariable Long id) {
    	return produtoRepository.findById(id)
	    	.map(resposta -> {
	    		produtoRepository.findById(id);
	    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    	})
	    	.orElse(ResponseEntity.notFound().build());
    }
    
}