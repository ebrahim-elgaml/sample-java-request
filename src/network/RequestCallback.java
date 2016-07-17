package network;

import models.Country;

/**
 * Created by ebrahim-elgaml on 17/07/16.
 */
public interface RequestCallback {
	public void onSuccess(Country[] response);
	public void onFailure(String message);
}
