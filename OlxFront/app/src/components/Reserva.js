import React from 'react'
import { Button, Heading, Pane, TextInputField, PlusIcon, Text, SelectField, Position, toaster, TextareaField } from 'evergreen-ui'
import Calendario from './Calendario'
import QuadraService from '../api/QuadraService'
import HorarioService from '../api/HorarioService';

class FormQuadra extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			quadra:{
				nome:'',
				endereco:'',
				descricao:'',
				esportes:'',
				preco:0,
				idUsuario:props.idUsuario
			},
			tmpHorario:{ diaSemana:0, hora:0},
            horarios : [
			]
		}
		this.quadraService = new QuadraService();
		this.horarioService = new HorarioService();
		this.adicionarHorario = this.adicionarHorario.bind(this);
		this.cadastrarQuadra = this.cadastrarQuadra.bind(this);
	}

	cadastrarQuadra(){
		this.quadraService.post("", this.state.quadra).then((res)=>{
			console.log(res);
			this.props.onNovaQuadra();
			this.props.close();
			
			this.state.horarios.forEach((h, i)=>{
				h["idQuadra"] = res.data.id;
				console.log(h);
				this.horarioService.post("", h);
			});

			toaster.success("Quadra cadastrada.");
		}).catch((err) => {
			toaster.danger("Erro ao cadastrar nova quadra.");
		});
	}
	
	adicionarHorario(e){
		e.preventDefault();
		e.target.reset();
		let hora =this.state.tmpHorario.hora;
		let diaSemana =this.state.tmpHorario.diaSemana;
		let iguais = this.state.horarios.filter((h)=> h.hora===hora && h.diaSemana===diaSemana)

		if(iguais.some((h)=>h)){
			toaster.warning("Horário já registrado.");
			return
		}

		this.state.horarios.push({
			diaSemana:diaSemana,
			hora:hora,
			minuto:0
		});

		this.setState({
			tmpHorario:{ diaSemana:0, hora:0},
			horarios:this.state.horarios
		});
	}

	render() {
		return (
		<div>
			<Pane padding="2em">
				<form>
					<TextInputField
					onChange={(e)=> this.state.quadra.nome = e.target.value}
					required
					label="Nome:"
					autoComplete={false}
					placeholder="ex.: Quadra Gonçalves Dias"/>

					<TextInputField
					onChange={(e)=> this.state.quadra.endereco = e.target.value}
					required
					autoComplete={false}
					label="Endereço:"
					placeholder="ex.: Rua das Begônias, n° 38"/>

					<TextInputField
					autoComplete={false}
					onChange={(e)=> this.state.quadra.esportes = e.target.value}
					required
					label="Esportes:"
					placeholder="ex.: Vôlei, Vôlei de Praia"/>

					<TextInputField
					autoComplete={false}
					onChange={(e)=> this.state.quadra.preco = e.target.value}
					type="number" min="1" step="any"
					required
					label="Preço (por uma hora)"
					placeholder="ex.: 18"/>
					
					<TextareaField
					autoComplete={false}
					onChange={(e)=> this.state.quadra.descricao = e.target.value}
					required
					label="Adicione uma descrição"
					placeholder="ex.: Temos sauna!"/>
					
				</form>

				<Heading>
					Horário de Funcionamento:
				</Heading>
				
				<Calendario horarios={this.state.horarios}>

				</Calendario>

				<form onSubmit={this.adicionarHorario}>
					<Pane display="flex">
						<TextInputField flex={1} marginRight="1em"
							onChange={(e)=>this.state.tmpHorario.hora = parseInt(e.target.value)}
							type="number"
							step="1" min="0" max="23"
							label="Hora"
							autoComplete={false}
							placeholder="ex.: 1"/>

						<SelectField defaultValue={0} label="Dia" onChange={(e)=>this.state.tmpHorario.diaSemana = parseInt(e.target.value)} width="30%" flex={1}>
							<option value={0}>DOM</option>
							<option value={1}>SEG</option>
							<option value={2}>TER</option>
							<option value={3}>QUA</option>
							<option value={4}>QUI</option>
							<option value={5}>SEX</option>
							<option value={6}>SAB</option>
						</SelectField>
					</Pane>
					<Pane width="100%" textAlign="right">
						<Button
						intent="success" 
						appearance="primary" 
						iconBefore={PlusIcon}>
							Adicionar Horário
						</Button>
					</Pane>
				</form>
				<Button
					marginTop={30}
					justifyContent="center"
					intent="success" 
					appearance="primary" 
					width="100%"
					onClick={this.cadastrarQuadra}
					>
							Salvar
				</Button>
			</Pane>
		</div>
		)
	}
}
export default FormQuadra