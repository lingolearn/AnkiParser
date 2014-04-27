package cscie99.team2.lingolearn.shared;

import java.io.Serializable;

/**
 * A single card within a Deck of cards.
 */
public class Card implements Serializable {

	private static final long serialVersionUID = -2630264168091602483L;
	
	private Long cardId;			// Unique card Id
	private String desc;			// Card's Description
	private String kanji;			// Kanji Unicode
	private	String hiragana;		// Hiragana Unicode
	private	String katakana;		// Katakana Unicode
	private String translation; 	// Translation
	private String nativeLanguage;	// Native language of the translation, example "en-us"
	private Image image;			// Image
	private Sound sound;			// Sound

	/**
	 * Constructor.
	 */
	public Card () {
		if( this.nativeLanguage == null )
			this.nativeLanguage = "English";
		
		this.kanji = "";
		this.hiragana = "";
		this.katakana = "";
	};
	
	/**
	 * Constructor.
	 */
	public Card(String kanji, String hiragana, String katakana,
			String translation, String nativeLanguage, String description) {
		this.kanji = kanji;
		this.hiragana = hiragana;
		this.katakana = katakana;
		this.translation = translation;
		this.nativeLanguage = nativeLanguage;
		this.desc = description;
	}


	public Long getId() {
		return cardId;
	}

	public void setId(Long cardId) {
		this.cardId = cardId;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getHiragana() {
		return hiragana;
	}

	public void setHiragana(String hiragana) {
		this.hiragana = hiragana;
	}

	public String getKatakana() {
		return katakana;
	}

	public void setKatakana(String katakana) {
		this.katakana = katakana;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getNativeLanguage() {
		return nativeLanguage;
	}

	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result
				+ ((hiragana == null) ? 0 : hiragana.hashCode());
		result = prime * result + ((kanji == null) ? 0 : kanji.hashCode());
		result = prime * result
				+ ((katakana == null) ? 0 : katakana.hashCode());

		result = prime * result
				+ ((translation == null) ? 0 : translation.hashCode());
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
		Card other = (Card) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (hiragana == null) {
			if (other.hiragana != null)
				return false;
		} else if (!hiragana.equals(other.hiragana))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (kanji == null) {
			if (other.kanji != null)
				return false;
		} else if (!kanji.equals(other.kanji))
			return false;
		if (katakana == null) {
			if (other.katakana != null)
				return false;
		} else if (!katakana.equals(other.katakana))
			return false;
		if (nativeLanguage == null) {
			if (other.nativeLanguage != null)
				return false;
		} else if (!nativeLanguage.equals(other.nativeLanguage))
			return false;
		if (sound == null) {
			if (other.sound != null)
				return false;
		} else if (!sound.equals(other.sound))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}	
	
	
}