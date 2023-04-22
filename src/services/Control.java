package services;

import model.Usuario;

public class Control {
	private Usuario sessionuser = null;

	public Control() {
		super();
	}

	/**
	 * @return the sessionuser
	 */
	public Usuario getSessionuser() {
		return sessionuser;
	}

	/**
	 * @param sessionuser the sessionuser to set
	 */
	public void setSessionuser(Usuario sessionuser) {
		this.sessionuser = sessionuser;
	}
	

}
