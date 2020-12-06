import React from 'react'
import MainHeader from '../components/MainHeader'
import {withRouter} from 'react-router-dom'
import {Pane} from 'evergreen-ui'

class Home extends React.Component {
  render() {
    return (
      <Pane paddingX="5em">
        <MainHeader></MainHeader>
      </Pane>
    )
  }
}

export default withRouter(Home)