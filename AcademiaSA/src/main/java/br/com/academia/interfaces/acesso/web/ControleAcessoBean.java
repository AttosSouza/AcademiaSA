package br.com.academia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.academia.application.service.AcessoService;
import br.com.academia.application.util.ValidationException;
import br.com.academia.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String Matricula;
	private Integer rg;
	
	public String registrarAcesso() {
		TipoAcesso tipoAcesso; 
		try {
			tipoAcesso = acessoService.registrarAcesso(Matricula, rg);
		}catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		
		String msg;
		
		if(tipoAcesso == TipoAcesso.Entrada) {
			msg = "Entrada registrada!";
		} else if (tipoAcesso == TipoAcesso.Saida) {
			msg = "Saída registrada!";
		} else {
			msg = "Dados de registro de acesso inconsistente!";
		}
		
		facesContext.addMessage(null, new FacesMessage(msg));
		return null;
	}
	
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public Integer getRg() {
		return rg;
	}
	public void setRg(Integer rg) {
		this.rg = rg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
