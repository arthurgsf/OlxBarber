import React from 'react'
import { Button, Heading, Pane, TextInputField, majorScale } from 'evergreen-ui'
class FormCadastro extends React.Component {
  render() {
    return (
      <div>
        <Pane padding="2em">
            <form>
                <Heading is="h2">Mais de 1.000 quadras poliesportivas para você!</Heading>
                <TextInputField
                required
                label="Nome Completo:"
                placeholder="ex.: Maria das Graças Neves"/>

                <TextInputField
                required
                type="email"
                label="Email:"
                placeholder="ex.: exemplo@mail.com"/>

                <TextInputField
                required
                type="number"
                label="Telefone:"
                placeholder="ex.: (DDD) 99999-9999"/>

                <TextInputField type="password"
                required
                label="Senha:"
                placeholder="*********"/>

                <TextInputField type="password"
                required
                label="Confirme a senha:"
                placeholder="*********"/>
                <Button appearance="primary" intent = "success" width="100%" justifyContent="center" height= {majorScale(6)} > Cadastrar</Button>
            </form>
        </Pane>
      </div>
    )
  }
}
export default FormCadastro