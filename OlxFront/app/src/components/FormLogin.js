import React from 'react'
import { Button, Pane, TextInputField, majorScale, toaster } from 'evergreen-ui'
import UsuarioService from '../api/UsuarioService'
import { withRouter } from 'react-router-dom';


class FormCadastro extends React.Component {

    constructor(){
        super();
        this.usrService = new UsuarioService();

        this.state = {
            usr:{
                email:'',
                senha:''
            }
        }

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    login(){
        let h = this.props.history;
        this.usrService.autenticar(this.state.usr)
        .then(res=>{
            h.push({pathname:"/main", state:res.data},);
        })
        .catch(err=>{
            console.log(err);
            toaster.danger('Usuário ou senha inválidos')
        });
    }

    handleSubmit(e){
        this.login();
        e.preventDefault();
    }

    render() {
        return (
        <div>
            <Pane padding="2em">
                <form onSubmit={this.handleSubmit}>
                    <TextInputField
                    name="email"
                    onChange={(e)=> this.state.usr.email = e.target.value}
                    required
                    type="email"
                    label="Email:"
                    placeholder="ex.: exemplo@mail.com"/>

                    

                    <TextInputField type="password"
                    name="senha"
                    onChange={(e)=> this.state.usr.senha = e.target.value}
                    required
                    label="Senha:"
                    placeholder="*********"/>

                    <Button 
                    appearance="primary" 
                    intent = "success" 
                    width="100%" 
                    justifyContent="center" 
                    height= {majorScale(6)} > 
                        Login
                    </Button>
                </form>
            </Pane>
        </div>
        )
    }
}
export default withRouter(FormCadastro)