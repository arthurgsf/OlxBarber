import ApiService from '../api/ApiService'

class HorarioService extends ApiService {

    constructor () {
        super('/api/funcionamento')
    }

    // listar(){
    //     return this.get("/todas");
    // }
}
export default HorarioService