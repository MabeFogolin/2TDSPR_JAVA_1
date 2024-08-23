package com._tdspr.mariabeatriz.gateways;

import com._tdspr.mariabeatriz.domains.Aluno;
import com._tdspr.mariabeatriz.gateways.requests.AlunoPatchNome;
import com._tdspr.mariabeatriz.gateways.requests.AlunoPostRequests;
import com._tdspr.mariabeatriz.gateways.responses.AlunoResponse;
import com._tdspr.mariabeatriz.usecase.CadastrarAluno;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers são pontos de entrada da aplicação. Eles são responsáveis por expor os endpoints que ficaram disponiveis para uso <p>
 * {@link RestController RestController} indica ao spring que deve expor os metodos publicos anotados com XXXMapping para os devidos endpoints e seus verbos
 * {@link RequestMapping RequestMapping} indica que há uma caminho padrão a ser aplicado antes de cada endponit, ex: <p>
 * Classe com RequestMapping(/aluno) <p>
 * Método com GetMapping(/fiap)<p>
 * o endpoint exposto final para esse conjunto será: "/aluno/fiap" <p>
 * @see RestController
 * @see RequestMapping
 * @see GetMapping
 * @see PutMapping
 * @see PostMapping
 * @see PatchMapping
 * @see org.springframework.web.bind.annotation.DeleteMapping
 *
 */

@RestController
@RequestMapping("/aluno/fiap")
@RequiredArgsConstructor
public class AlunoController {

    private final CadastrarAluno cadastraAluno;
    /**
     * {@link ResponseEntity} é um retorno que dá mais liberdade para manipular os status code que podemos entregar na API
     *
     */
    @GetMapping
    public ResponseEntity<String> getAlunos() {
        return ResponseEntity.ok( "Hello World");
    }

    /**
     * Também podemos retornar o propiro objeto sem fazer uso de ResponseEntity, porem perdemos a habilidade de manipular explicitamente o status code <p>
     * Assim fazemos uso do {@link ResponseStatus} para deixar claro qual o status de sucesso deve ser exposto
     *
     */
    @GetMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno getAluno(@PathVariable String alunoId) {
        return new Aluno();
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> postAluno(@RequestBody AlunoPostRequests aluno) {
        String[] nomeSplitado = aluno.nomeCompleto().split(" ");
        Aluno alunoAtualizado = new Aluno(nomeSplitado[0], nomeSplitado[1],aluno.documento(), aluno.registro());
        alunoAtualizado.setRm("23456789ssdfgh");
        AlunoResponse alunoResponse = AlunoResponse.builder()
                .primeiroNome(alunoAtualizado.getPrimeiroNome())
                .sobrenome(alunoAtualizado.getSobrenome())
                .documento(alunoAtualizado.getDocumento())
                .registro(alunoAtualizado.getRegistro())
                .build();
        return ResponseEntity.ok(alunoResponse);
    }


    @PatchMapping("/{alunoId}/nome")
    public ResponseEntity<AlunoResponse> atualizaNome(@PathVariable String alunoId, @RequestBody AlunoPatchNome nome ) {
        return null;
    }

}
