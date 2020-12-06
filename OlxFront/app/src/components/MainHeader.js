import React from 'react'
import FormCadastro from './FormCadastro'
import FormLogin from './FormLogin'
import {
    SearchInput,
    Button,
    majorScale,
    Pane,
    Heading,
    Popover,
    Position
} from 'evergreen-ui'

class MainHeader extends React.Component {
    render () {
        return (
            <Pane display="flex" paddingX="5em">
                <Pane flex={1} alignItems="center" display="flex">
                    <Heading cursor="pointer" is="h1">OQB</Heading>
                </Pane>
                <Pane >
                    <SearchInput placeholder="buscar" marginRight="4em"></SearchInput>
                    <Popover content={<FormCadastro/>} position={Position.BOTTOM_RIGHT} minWidth="410px">
                        <Button appearance="minimal" height={majorScale(5)} marginRight={64}>Registrar</Button>
                    </Popover>

                    <Popover content={<FormLogin/>} position={Position.BOTTOM_RIGHT} minWidth="410px">
                        <Button appearance="minimal" intent="success" height={majorScale(5)}>Login</Button>                        
                    </Popover>
                </Pane>
            </Pane>
        )
    }
}
export default MainHeader