import React from 'react'
import {
    SearchInput,
    Button,
    majorScale,
    Pane,
    Heading,    
} from 'evergreen-ui'

class MainHeader extends React.Component {
    render () {
        return (
            <Pane display="flex">
                <Pane flex={1} alignItems="center" display="flex">
                    <Heading cursor="pointer" is="h1">OQB</Heading>
                </Pane>
                <Pane >
                    <Button appearance="minimal" intent="danger" height={majorScale(5)}>Logout</Button>
                </Pane>
            </Pane>
        )
    }
}
export default MainHeader