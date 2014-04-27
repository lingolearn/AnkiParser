package cscie99.team2.lingolearn.server.anki.models;

public class CardModelTemplate {

	private String afmt;
	private String name;
	private String gfmt;
	private long did;
	private int ord;
	private String bafmt;
	private String bgfmt;
	
	public CardModelTemplate( String afmt, String name, String gfmt,
				long did, int ord, String bafmt, String bgfmt ){
		setAfmt(afmt);
		setName(name);
		setGfmt(gfmt);
		setDid(did);
		setOrd(ord);
		setBafmt(bafmt);
		setBgfmt(bgfmt);
	}
	
	public String getAfmt() {
		return afmt;
	}
	public void setAfmt(String afmt) {
		this.afmt = afmt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGfmt() {
		return gfmt;
	}
	public void setGfmt(String gfmt) {
		this.gfmt = gfmt;
	}
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public String getBafmt() {
		return bafmt;
	}
	public void setBafmt(String bafmt) {
		this.bafmt = bafmt;
	}
	public String getBgfmt() {
		return bgfmt;
	}
	public void setBgfmt(String bgfmt) {
		this.bgfmt = bgfmt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afmt == null) ? 0 : afmt.hashCode());
		result = prime * result + ((bafmt == null) ? 0 : bafmt.hashCode());
		result = prime * result + ((bgfmt == null) ? 0 : bgfmt.hashCode());
		result = prime * result + (int) (did ^ (did >>> 32));
		result = prime * result + ((gfmt == null) ? 0 : gfmt.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ord;
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
		CardModelTemplate other = (CardModelTemplate) obj;
		if (afmt == null) {
			if (other.afmt != null)
				return false;
		} else if (!afmt.equals(other.afmt))
			return false;
		if (bafmt == null) {
			if (other.bafmt != null)
				return false;
		} else if (!bafmt.equals(other.bafmt))
			return false;
		if (bgfmt == null) {
			if (other.bgfmt != null)
				return false;
		} else if (!bgfmt.equals(other.bgfmt))
			return false;
		if (did != other.did)
			return false;
		if (gfmt == null) {
			if (other.gfmt != null)
				return false;
		} else if (!gfmt.equals(other.gfmt))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ord != other.ord)
			return false;
		return true;
	}
	
	
	
}
