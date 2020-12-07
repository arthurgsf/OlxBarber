import React from 'react'
import { Button, Heading, Pane, TextInputField, PlusIcon, Text, SelectField, Position, toaster, TextareaField } from 'evergreen-ui'
import UsuarioService from '../api/UsuarioService'
import { Button, Heading, Pane, TextInputField, majorScale } from 'evergreen-ui'

class FormCadastro extends React.Component {
  constructor(props){
		super(props);
		this.state = {
			usuario:{
				nome:'',
				email:'',
				telefone:'',
				senha:''
      }
    }
		this.usuarioService = new UsuarioService();
		this.cadastrarUsuario = this.cadastrarUsuario.bind(this);
  }

  cadastrarUsuario(){
		this.usuarioService.post("", this.state.usuario).then((res)=>{
			console.log(res);
			this.props.onNovaUsuario();
			this.props.close();
			toaster.success("Usuário cadastrado.");
		}).catch((err) => {
			toaster.danger("Erro ao cadastrar novo usuário.");
		});
	}
  
  render() {
    return (
      <div>
        <Pane padding="2em">
            <form>
                <Heading is="h2">Mais de 1.000 quadras poliesportivas para você!</Heading>
                <TextInputField
                onChange={(e)=> this.state.usuario.nome = e.target.value}
                required
                label="Nome Completo:"
                placeholder="ex.: Maria das Graças Neves"/>

                <TextInputField
                onChange={(e)=> this.state.usuario.email = e.target.value}
                required
                type="email"
                label="Email:"
                placeholder="ex.: exemplo@mail.com"/>

                <TextInputField
                onChange={(e)=> this.state.usuario.telefone = e.target.value}
                required
                type="number"
                label="Telefone:"
                placeholder="ex.: (DDD) 99999-9999"/>

                <TextInputField type="password"
                onChange={(e)=> this.state.usuario.senha = e.target.value}
                required
                label="Senha:"
                placeholder="*********"/>

                <TextInputField type="password"
                required
                label="Confirme a senha:"
                placeholder="*********"/>
                <Button appearance="primary" intent = "success" width="100%" justifyContent="center" height= {majorScale(6)} > Cadastrar</Button>
            </form>
            <Button
              marginTop={30}
              justifyContent="center"
              intent="success" 
              appearance="primary" 
              width="100%"
              onClick={this.cadastrarUsuario}
              >
                  Cadastrar
            </Button>
        </Pane>
      </div>
    )
  }
}
export default FormCadastro