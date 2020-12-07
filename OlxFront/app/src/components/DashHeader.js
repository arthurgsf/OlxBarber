import React from 'react'
import { withRouter } from 'react-router'
import {
    Button,
    PlusIcon,
    majorScale,
    Pane,
    Heading,
    Dialog    
} from 'evergreen-ui'
import FormQuadra from './FormQuadra';

class MainHeader extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            dialogShow:false,
        }
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout(){
        this.props.history.push("/");
    }

    render () {
        return (
            <Pane display="flex">
                <Pane flex={1} alignItems="center" display="flex">
                    <Heading cursor="pointer" is="h1">OQB</Heading>
                </Pane>
                <Pane>
                    <Dialog
                        isShown={this.state.dialogShow}
                        title="Cadastrar Quadra"
                        shouldCloseOnOverlayClick={false}
                        onCloseComplete={() => this.setState({ dialogShow: false })}
                        hasFooter={false}
                    >
                        {
                            ({close})=> (
                                <FormQuadra onNovaQuadra={this.props.onNovaQuadra} idUsuario={this.props.idUsuario} close={close}>
                                </FormQuadra>
                            )
                        }
                    </Dialog>
                    <Button
                    onClick={() => this.setState({ dialogShow: true })}
                    appearance="minimal"
                    intent="success"
                    iconBefore={PlusIcon}
                    height={majorScale(5)}>Adicionar Quadra</Button>

                    <Button
                    onClick={this.handleLogout}
                    appearance="minimal"
                    intent="danger"
                    height={majorScale(5)}>Logout</Button>
                </Pane>
            </Pane>
        )
    }
}
export default withRouter(MainHeader)