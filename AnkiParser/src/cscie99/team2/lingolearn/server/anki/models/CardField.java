package cscie99.team2.lingolearn.server.anki.models;

import java.util.Arrays;


//@JsonIgnoreProperties({"media"})
public class CardField {
	
	private String name;
	private String media[];
	private boolean sticky;
	private boolean rtl;
	private int ord;
	private String font;
	private int size;

	public CardField(){}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	public boolean isSticky() {
		return sticky;
	}
	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}
	public boolean isRtl() {
		return rtl;
	}
	public void setRtl(boolean rtl) {
		this.rtl = rtl;
	}
	
	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String[] getMedia() {
		return media;
	}
	public void setMedia(String[] media) {
		this.media = media;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + Arrays.hashCode(media);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ord;
		result = prime * result + (rtl ? 1231 : 1237);
		result = prime * result + size;
		result = prime * result + (sticky ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardField other = (CardField) obj;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (!Arrays.equals(media, other.media))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ord != other.ord)
			return false;
		if (rtl != other.rtl)
			return false;
		if (size != other.size)
			return false;
		if (sticky != other.sticky)
			return false;
		return true;
	}
	
	
	
}
