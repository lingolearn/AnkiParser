/**
 * CSCIE99 TEAM 2
 */
package cscie99.team2.lingolearn.shared;

import java.io.Serializable;


/**
 * CSCIE99 TEAM 2
 */

/**
 * @author YPolyanskyy
 *
 * This class represents Image, corresponding to the individual card.
 */
public class Image implements Serializable {
	private static final long serialVersionUID = -4763749011775976391L;
		
	private Long imageId;
	private String imageUri;	// Location of the image file in the datastore

	
	public Image () {};
		
	public String getImageUri() {
		return this.imageUri;
	}
	
	public Long getImageId() {
		return this.imageId;
	}
	
	public void setImageUri(String uri) {
		this.imageUri = uri;
	}
	
	public void setImageId(Long id) {
		this.imageId = id;
	}
}
