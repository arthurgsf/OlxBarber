import ApiService from '../api/ApiService'

class QuadraService extends ApiService {

    constructor () {
        super('/api/quadra')
    }

    listar(){
        return this.get("/todas");
    }
}
export default QuadraService