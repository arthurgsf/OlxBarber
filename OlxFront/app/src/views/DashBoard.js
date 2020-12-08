import React from 'react'
import DashHeader from '../components/DashHeader'
import {withRouter} from 'react-router-dom'
import axios from 'axios'
import { Heading, Pane, Small, Text, TimeIcon, Button, SearchInput, Autocomplete, majorScale, Badge } from 'evergreen-ui'
import QuadraService from '../api/QuadraService'

class DashBoard extends React.Component {
    constructor(props){
        super(props);

        this.quadraService = new QuadraService();
        this.state = {
            quadras:[],
            busca:[]
        }

        this.busca = this.busca.bind(this);
        this.carregarQuadras = this.carregarQuadras.bind(this);
    }

    busca (e) {
        let strBusca = e.target.value;
        this.setState({
            quadras:this.state.quadras,
            busca:this.state.quadras.filter((q)=> 
                q.nome.toLowerCase().includes(strBusca.toLowerCase().trim())||
                q.endereco.toLowerCase().includes(strBusca.toLowerCase().trim())||
                q.esportes.toLowerCase().includes(strBusca.toLowerCase().trim())
            )})
    }

    render() {
        return (

        <Pane paddingX="5em">
            <DashHeader onNovaQuadra={this.carregarQuadras} idUsuario={this.props.location.state}></DashHeader>
            <Pane width="100%">
                <Pane marginX="auto" marginY={60}  alignItems="center">
                    <SearchInput placeholder="Buscar" width="100%" height={majorScale(6)} onChange={this.busca}/>
                    {this.state.busca.map((q, idx)=>(
                        <Pane minHeight={150} padding={24} elevation={1} marginY={10}>
                            <Heading>{q.nome.replace(/\w\S*/g,(txt)=> txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase())}
                            </Heading>
                            <Text>{q.descricao}</Text>
                            <Button appearance="minimal" intent="none" iconBefore={TimeIcon}>
                                Reservar
                            </Button>
                            <Pane>
                                <Text><Small>{q.endereco.replace(/\w\S*/g,(txt)=> txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase())}</Small></Text>
                            </Pane>
                            <Pane>
                                {q.esportes.split(',').map((e, i)=>{
                                        return(
                                            <Badge marginRight="2em">{e}</Badge>
                                        )
                                    })}
                            </Pane>
                        </Pane>
                    ))}
                </Pane>
            </Pane>
        </Pane>
        )
    }

    carregarQuadras(){
        this.quadraService.listar().then((res)=>{
            this.setState({quadras:res.data, busca:res.data});
        }).catch((err)=>{
            console.log(err);
        });
    }

    componentDidMount() {
        this.carregarQuadras();
    }
}

export default withRouter(DashBoard)