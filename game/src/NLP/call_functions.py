# récupération de l'entrée utilisateur nettoyée (partie de Solène)
from cleaning_and_parsing import clean_words
from cleaning_and_parsing import tagged_words
import nltk
from nltk.corpus import wordnet

"""
Sorties possibles :
déplacement:
    forward
    backward
    backtrack
    moveTo
attaque
voir équipement
utiliser objet

data:
ARMES
Pair.with("Club", 1),
Pair.with("Knife", 2),
Pair.with("Dagger", 3),
Pair.with("Bow", 4),
Pair.with("Sword", 5),
Pair.with("Axe", 6),
Pair.with("Whip", 7),
Pair.with("Claymore", 8),
Pair.with("Scythe", 9),
Pair.with("Magic AK47", 10)));

MONSTRES

Pair.with("Zombie", 1),
Pair.with("Skeleton", 2),
Pair.with("Ghost", 3),
Pair.with("Undead", 4),
Pair.with("Wolf", 5),
Pair.with("Giant spider", 6),
Pair.with("Slime", 7),
Pair.with("Ghoul", 8),
Pair.with("Necromancer", 9),
Pair.with("Dragon", 10)));
"""

#ATTENTION : on renverra la liste suivante : verbe, cible du verbe, moyen 

#on commnence par les verbes
# on considère qu'il n'y a qu'un verbe dans la phrase, on le récupère dans la liste
verb = ""
for i in clean_words:
    print(i, i[0], i[1])
    print('V' in i[1])
    if 'V' in i[1]:
        verb = i[0]
        print(type(i[0]))
        break

print(verb)
if verb == "":
    print("Error ! Verb not found")
else:

    # verbes de référence : move, attack, use, look
    # ces verbes servent à classifier le verbe donné par l'utilisateur
    # dans une des actions possibles correspondantes

    ref_vbs = ["move", "attack", "look", "take", "throw", "equip", "check"]
    syn_tab = []
    # il existe 2 approches possibles pour faire la correspondance
    # première méthode : on cherche dans les synonymes des mots de référence
    print("----- matching with synonyms -----")
    for ref in ref_vbs:
        tmp = []
        for words in wordnet.synsets(ref):
            for lemma in words.lemmas():
                tmp.append(lemma.name())
                #print(lemma.name())
        syn_tab.append(tmp)
    

    # on rajoute a la main certains mots qui résultent à la meme action dans le jeu
    for words in wordnet.synsets("Kill"):
        for lemma in words.lemmas():
            syn_tab[1].append(lemma.name())



    if verb in syn_tab[0]:
        print("Action : move")
    elif verb in syn_tab[1]:
        print("Action : attack")
    elif verb in syn_tab[2]:
        print("Action : use")
    elif verb in syn_tab[3]:
        print("Action : look")
    else:
        print("Error : verb unmatched")


    print("----- matching with similarity -----")
    # deuxième méthode : on utilise une fonction
    # donnant un indicateur de similarité entre deux mots
    match_tab = []
    for i in ref_vbs:
        ref = wordnet.synsets(i, "v")[0]
        word = wordnet.synsets(verb, "v")[0]
        match_tab.append((ref.wup_similarity(word), i))

    match_tab.sort(reverse=True)
    print(match_tab)
    print("Action : %s" % match_tab[0][1])






#on essaie de regrouper par groupe de sens

grammar = "NP : {<DT>?<JJ>*<NN>}"

parser = nltk.RegexpParser(grammar)
output = parser.parse(tagged_words)
print(output)
#output.draw()