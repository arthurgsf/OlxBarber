import React from 'react'
import {withRouter} from 'react-router-dom'
import { Button, Heading, Pane, TextInputField, PlusIcon, Text, SelectField, Position, toaster, TextareaField, majorScale } from 'evergreen-ui'
import UsuarioService from '../api/UsuarioService'

class FormCadastro extends React.Component {
  	constructor(props){
		super(props);
		this.state = {
			usuario:{
				nome:'',
				email:'',
				telefone:'',
				senha:''
      		},
      		confirmarSenha:'',
    	}
		this.usuarioService = new UsuarioService();
		this.cadastrarUsuario = this.cadastrarUsuario.bind(this);
  	}

	cadastrarUsuario(e){
		e.preventDefault();
		e.target.reset();

		if(this.state.confirmarSenha === this.state.usuario.confirmarSenha){
			toaster.danger("As senhas informadas não correspondem");
			return;
		}

		this.usuarioService.post("", this.state.usuario).then((res)=>{
			toaster.success("Usuário cadastrado.");
			console.log(res.data);
			this.props.history.push({
				pathname:"/main", 
				state:res.data.id,
			});
		}).catch((err) => {
			console.log(err)
			toaster.danger("Erro ao cadastrar novo usuário.");
		});
	}
  
  render() {
    return (
      <div>
        <Pane padding="2em">
            <form onSubmit={this.cadastrarUsuario}>
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
				onChange={(e)=>this.state.confirmarSenha = e.target.value}
                label="Confirme a senha:"
                placeholder="*********"/>

                <Button 
                appearance="primary" 
                intent = "success" 
                width="100%" 
                justifyContent="center"
                height= {majorScale(6)} >
                  	Cadastrar
                </Button>
            </form>
        </Pane>
      </div>
    )
  }
}
export default withRouter(FormCadastro)