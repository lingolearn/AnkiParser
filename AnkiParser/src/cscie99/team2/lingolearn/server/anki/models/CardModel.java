package cscie99.team2.lingolearn.server.anki.models;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"req", "tmpls"})
public class CardModel {

	//private long modelId;				// model id for cols table => notes table
	private String vers[];				// ?
	private String name;				// name of deck / deck type
	private String tags[];				// category tags for deck
	private long did;					// another serial pik ?
	private int usn;
	private String req[];
	
	private CardField flds[];			// card fields
	private int sortf;
	private String latexPre;			// latex stuff
	
	private CardModelTemplate []tmpls;
	
	private String latexPost;
	private int type;
	private long id;
	private String css;
	private long mod;
	
	public CardModel(){}
	
	/*
	public long getModelId() {
		return modelId;
	}
	public void setModelId(long modelId) {
		this.modelId = modelId;
	}
	*/
	public String[] getVers() {
		return vers;
	}
	public void setVers(String[] vers) {
		this.vers = vers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public int getUsn() {
		return usn;
	}
	public void setUsn(int usn) {
		this.usn = usn;
	}
	public String[] getReq() {
		return req;
	}
	public void setReq(String[] req) {
		this.req = req;
	}
	
	public CardField[] getFlds() {
		return flds;
	}

	public void setFlds(CardField[] flds) {
		this.flds = flds;
	}

	public int getSortf() {
		return sortf;
	}
	public void setSortf(int sortf) {
		this.sortf = sortf;
	}
	public String getLatexPre() {
		return latexPre;
	}
	public void setLatexPre(String latexPre) {
		this.latexPre = latexPre;
	}
	public CardModelTemplate[] getTmpls() {
		return tmpls;
	}
	public void setTmpls(CardModelTemplate[] tmpls) {
		this.tmpls = tmpls;
	}
	public String getLatexPost() {
		return latexPost;
	}
	public void setLatexPost(String latexPost) {
		this.latexPost = latexPost;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public long getMod() {
		return mod;
	}
	public void setMod(long mod) {
		this.mod = mod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((css == null) ? 0 : css.hashCode());
		result = prime * result + (int) (did ^ (did >>> 32));
		result = prime * result + Arrays.hashCode(flds);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((latexPost == null) ? 0 : latexPost.hashCode());
		result = prime * result
				+ ((latexPre == null) ? 0 : latexPre.hashCode());
		result = prime * result + (int) (mod ^ (mod >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(req);
		result = prime * result + sortf;
		result = prime * result + Arrays.hashCode(tags);
		result = prime * result + Arrays.hashCode(tmpls);
		result = prime * result + type;
		result = prime * result + usn;
		result = prime * result + Arrays.hashCode(vers);
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
		CardModel other = (CardModel) obj;
		if (css == null) {
			if (other.css != null)
				return false;
		} else if (!css.equals(other.css))
			return false;
		if (did != other.did)
			return false;
		if (!Arrays.equals(flds, other.flds))
			return false;
		if (id != other.id)
			return false;
		if (latexPost == null) {
			if (other.latexPost != null)
				return false;
		} else if (!latexPost.equals(other.latexPost))
			return false;
		if (latexPre == null) {
			if (other.latexPre != null)
				return false;
		} else if (!latexPre.equals(other.latexPre))
			return false;
		if (mod != other.mod)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(req, other.req))
			return false;
		if (sortf != other.sortf)
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		if (!Arrays.equals(tmpls, other.tmpls))
			return false;
		if (type != other.type)
			return false;
		if (usn != other.usn)
			return false;
		if (!Arrays.equals(vers, other.vers))
			return false;
		return true;
	}
	
	
	
}
