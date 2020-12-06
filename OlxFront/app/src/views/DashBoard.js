import React from 'react'
import DashHeader from '../components/DashHeader'
import {withRouter} from 'react-router-dom'
import axios from 'axios'
import { Heading, Pane, Small, Text, TimeIcon, Button, TextInput, Autocomplete, majorScale } from 'evergreen-ui'
import QuadraService from '../api/QuadraService'

class DashBoard extends React.Component {
    constructor(props){
        super(props);

        this.quadraService = new QuadraService();
        this.state = {
            quadras:[],
        }

        this.buscar = this.buscar.bind(this);
    }
    
    getQuadras = ()=>{
        axios.get().then().catch();
    }

    buscar(strBusca){
        return this.state.quadras.map((q, i) => q.nome.includes(strBusca)||q.endereco.includes(strBusca));
    }

    render() {
        return (
            
        <Pane paddingX="5em">
            <DashHeader></DashHeader>
            <Pane width="100%">
                <Pane marginX="auto" marginY={60}  alignItems="center">
                    <Autocomplete
                    renderItem={(q)=>(
                        <Pane>
                            <Text>{q.nome}</Text>
                            <Text><Small>{q.endereco}</Small></Text>
                            
                        </Pane>
                    )}
                    items={this.state.quadras}
                    itemsFilter={this.buscar}
                    >
                        {(props)=>{
                            const { getInputProps, getRef, inputValue, openMenu } = props;
                            return (
                                <TextInput
                                marginBottom={60}
                                width="100%"
                                placeholder="Buscar"
                                height={majorScale(5)}
                                value={inputValue}
                                ref={getRef}
                                {...getInputProps()}/> 
                            )
                        }}
                    </Autocomplete>
                    {this.state.quadras.map((q, idx)=>(    
                        <Pane minHeight={150}maxHeight={200} padding={24} elevation={1} marginBottom={20}>
                            <Heading>{q.nome.replace(/\w\S*/g,(txt)=> txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase())}
                            </Heading>
                            <Text>{q.descricao}</Text>
                            <Button appearance="minimal" intent="none" iconBefore={TimeIcon}>
                                Reservar
                            </Button>
                            <Pane>
                                <Text><Small>{q.endereco.replace(/\w\S*/g,(txt)=> txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase())}</Small></Text>
                            </Pane>
                        </Pane>
                    ))}
                </Pane>
            </Pane>
        </Pane>
        )
    }

    componentDidMount() {
        this.quadraService.listar().then((res)=>{
            this.setState({quadras:res.data});
        }).catch((err)=>{
            console.log(err);
        });
    }
}

export default withRouter(DashBoard)