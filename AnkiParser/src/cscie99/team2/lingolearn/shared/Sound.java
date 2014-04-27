/**
 * CSCIE99 TEAM 2
 */
package cscie99.team2.lingolearn.shared;

import java.io.Serializable;

/**
 * @author YPolyanskyy
 * 
 * This class represents Sound, corresponding to the individual card.
 */
public class Sound implements Serializable {
	private static final long serialVersionUID = -4086241020207666390L;
	
	private Long soundId;		
	private String soundUri;	// Location of the sound file in the datastore
	
	public Sound() {}

	public String getSoundUri() {
		return this.soundUri;
	}
	
	public Long getSoundId() {
		return this.soundId;
	}

	public void setSoundUri(String soundUri) {
		this.soundUri = soundUri;
	}
	
	public void setSoundId(Long id) {
		this.soundId = id;
	}
}
