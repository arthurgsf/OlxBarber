package com.arthurgsf.oqb.controller;

import com.arthurgsf.oqb.model.dto.LoginDTO;
import com.arthurgsf.oqb.model.dto.UsuarioDTO;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usr")
public class UsuarioController {
    @Autowired
    UsuarioService usrService;

    /**
     * Adiciona usuário ao banco de dados
     * @param dto
     * @return 200 (sucesso) ou error
     */
    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody UsuarioDTO dto) {
        // builda um novo usuario a partir dos dados do DTO
        Usuario usr = Usuario.builder()
                        .nome(dto.getNome())
                        .email(dto.getEmail())
                        .senha(dto.getSenha())
                        .telefone(dto.getTelefone())
                        .build();

        // manda usuário service salvar o usuário criado
        try {
            Usuario salvo = usrService.salvar(usr);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }    
    }

    /**
     * Recebe um usuário e faz a validação do seu usuário e senha
     *
     * @param dto
     * @return
     */
    @PostMapping("/autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody LoginDTO dto) {
        try {
            Usuario usr = usrService.login(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok().body(usr.getId());
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Exclui um usuário a partir do email
     *
     * @param email
     * @return
     */
    @DeleteMapping
    public ResponseEntity deletarUsuario(@RequestParam String email){
        try{
            usrService.deletar(email);
            return ResponseEntity.ok(true);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // só vai ter update se a gente quiser enfeitar depois
    // @PostMapping
    // public ResponseEntity atualizarUsuario(@RequestBody UsuarioDto dto){
    //     try{
    //         usrService.;
    //         return ResponseEntity.ok(true);
    //     }catch(RuntimeException e){
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
}
