
import React from 'react'
import {
    Table,
    MinusIcon,
    TickIcon
} from 'evergreen-ui'

class Calendario extends React.Component {    
    state = {
        dias:["DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"],
        horas: [...Array(24).keys()]
    }

    diaParaTexto(dia){
        switch(dia){
            case 0:
                return "DOM";
            case 1:
                return "SEG";
            case 2:
                return "TER";
            case 3:
                return "QUA";
            case 4:
                return "QUI";
            case 5:
                return "SEX";
            case 6:
                return "SEX";
            default:
                return "";
        }
    }

    render () {
        return (
            <Table>
                <Table.Head>
                    <Table.TextHeaderCell flex={3}>
                        HORA
                    </Table.TextHeaderCell>
                    {
                        this.state.dias.map(d=>{
                            return(
                                <Table.TextHeaderCell key={d}>
                                    {d}
                                </Table.TextHeaderCell>
                            )
                        })
                    }
                </Table.Head>
                <Table.Body height={240}>

                    {this.state.horas.map((h, i) => {
                        let horasIguais = this.props.horarios.filter((horario)=> horario.hora===parseInt(h));
                        if(horasIguais.length > 0){
                            return (
                                <Table.Row key={h}>
                                    <Table.TextCell flex={3}>{h + ":00 - " + (h+1) +":00"}</Table.TextCell>
                                    {
                                        [0,1,2,3,4,5,6].map((n)=>{
                                            let marca = horasIguais.filter((h)=>h.diaSemana===n);
                                            if(marca.some((s)=>s)){
                                                return (
                                                    <Table.Cell><TickIcon></TickIcon></Table.Cell>
                                                )
                                            }else{
                                                return (<Table.Cell><MinusIcon></MinusIcon></Table.Cell>)
                                            }
                                        })
                                    }
                                </Table.Row>
                            )
                        }
                    })}
                </Table.Body>
            </Table>
        )
    }
}
export default Calendario