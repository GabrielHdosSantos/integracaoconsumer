package integracaomiddle.middle.controller;

import integracaomiddle.middle.dto.PessoaDTO;
import integracaomiddle.middle.modelo.Pessoa;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class controller {

    @PostMapping("/add")
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody PessoaDTO pessoaDTO){

        Pessoa pessoa = new Pessoa(pessoaDTO.nome, pessoaDTO.sobrenome);
        ResponseEntity<Pessoa> pessoasave = new RestTemplate().exchange("http://localhost:9095/pessoa/adicionar",
                HttpMethod.POST, new HttpEntity<>(pessoa),
                Pessoa.class);

        return pessoasave;

    }

    @PostMapping("/procurarPessoa")
    public ResponseEntity<Pessoa> achar (@RequestBody PessoaDTO pessoaDTO){

        Pessoa pessoa = new Pessoa(pessoaDTO.id);
        ResponseEntity<Pessoa> pessoafind = new RestTemplate().exchange("http://localhost:9095/pessoa/find", HttpMethod.POST, new HttpEntity<>(pessoa), Pessoa.class);

        return pessoafind;
    }


}
