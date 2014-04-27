package cscie99.team2.lingolearn.server.anki;

import cscie99.team2.lingolearn.shared.Card;
import cscie99.team2.lingolearn.shared.Deck;

public interface CardWriter {

	public String writeCard( Card card, Deck deck );
	
	public String writeMedia( Deck deck );
}
