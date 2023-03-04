package co.com.certificacion.reqres.stepdefinitions;

import co.com.certificacion.reqres.models.DatosUsuario;
import co.com.certificacion.reqres.questions.ObtenerCodigo;
import co.com.certificacion.reqres.questions.ObtenerFecha;
import co.com.certificacion.reqres.questions.ObtenerFechaDe;
import co.com.certificacion.reqres.questions.ObtenerValor;
import co.com.certificacion.reqres.tasks.ActualizarUsuario;
import co.com.certificacion.reqres.tasks.CrearUsuario;
import co.com.certificacion.reqres.tasks.EliminarUsuario;
import co.com.certificacion.reqres.tasks.ListarUsuarios;
import co.com.certificacion.reqres.utils.Constantes;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EscenariosReqresStepDefinitions {
    Actor usuario = Actor.named("user");
    DateFormat formateador= new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String fechaActual = formateador.format(date);

    @Given("^que el usuario cuenta con la api reqres$")
    public void queElUsuarioCuentaConLaApiReqres() {
        usuario.whoCan(CallAnApi.at(Constantes.URL_REQRES));
    }

    @When("^el usuario envia los datos de usuario a crear$")
    public void elUsuarioEnviaLosDatosDeUsuarioACrear(List<DatosUsuario> data) {
        usuario.attemptsTo(CrearUsuario.conApiReqres(data));
    }

    @Then("^la respuesta de la api presenta el codigo (.*)$")
    public void laRespuestaDeLaApiPresentaElCodigo(int codigoRespuesta) {
        usuario.should(GivenWhenThen.seeThat(ObtenerCodigo.deRespuesta(), Matchers.equalTo(codigoRespuesta)));
    }

    @And("^en la respuesta la fecha de creacion debe ser la fecha actual$")
    public void enLaRespuestaLaFechaDeCreacionDebeSerLaFechaActual() {

        usuario.should(GivenWhenThen.seeThat(ObtenerFecha.deCreacionUsuario(),Matchers.containsString(fechaActual)));
    }

    //LISTAR USUARIOS
    @When("^el usuario realice la peticion para listar usuarios$")
    public void elUsuarioRealiceLaPeticionParaListarUsuarios() {
        usuario.attemptsTo(ListarUsuarios.deApiReqres());
    }

    @And("^en la respuesta debe presentar el campo llamado total con valor (.*)$")
    public void enLaRespuestaDebePresentarElCampoLlamadoTotalConValor(String valor) {
        usuario.should(GivenWhenThen.seeThat(ObtenerValor.totalDeUsuarios(),Matchers.equalTo(valor)));
    }

    //ACTUALIZAR USUARIO
    @And("^el usuario luego envia los datos del usuario a actualizar$")
    public void elUsuarioLuegoEnviaLosDatosDelUsuarioAActualizar(List<DatosUsuario>data) {
        usuario.attemptsTo(ActualizarUsuario.enApiReqres(data));
    }

    @And("^en la respuesta la fecha de actualizacion debe ser la fecha actual$")
    public void enLaRespuestaLaFechaDeActualizacionDebeSerLaFechaActual() {
        usuario.should(GivenWhenThen.seeThat(ObtenerFechaDe.actualizacionUsuario(),Matchers.containsString(fechaActual)));
    }

    //METODO DELETE
    @And("^el usuario envia la peticion para eliminar usuario$")
    public void elUsuarioEnviaLaPeticionParaEliminarUsuario() {
        usuario.attemptsTo(EliminarUsuario.conApiReqres());
    }
}
