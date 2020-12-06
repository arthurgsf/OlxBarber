import ApiService from '../api/ApiService'

class UsuarioService extends ApiService {

    constructor () {
        super('/api/usr')
    }

    autenticar(objeto){
        return this.post("/autenticar", objeto);
    }
}
export default UsuarioService