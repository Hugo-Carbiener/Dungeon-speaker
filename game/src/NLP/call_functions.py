# récupération de l'entrée utilisateur nettoyée (partie de Solène)
import cleaning_and_parsing as cap
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
- move to location
- attack monster with weapon
- take item
- throw item
- equip item
- look
- check map/inventory
"""
# on renverra un tableau avec le verbe correspondant à l'action
# ainsi que les éventuels arguments
result = []

items = ["club", "knife", "dagger", "bow", "sword", "axe", "whip", "claymore", "scythe", "katana"]
monsters = ["zombie", "skeleton", "ghost", "undead", "wolf", "spider", "slime", "ghoul", "necromancer", "dragon"]
#ATTENTION : on renverra la liste suivante : verbe, cible du verbe, moyen 

#on commnence par les verbes
# on considère qu'il n'y a qu'un verbe dans la phrase, on le récupère dans la liste
verb = ""
for i in cap.clean_words:
    if 'V' in i[1]:
        verb = i[0]
        cap.clean_words.remove(i)
        break

if verb == "":
    print("ERROR")
else:

    # verbes de référence : move, attack, use, look
    # ces verbes servent à classifier le verbe donné par l'utilisateur
    # dans une des actions possibles correspondantes

    ref_vbs = [
        ["move", "go", "run"], 
        ["attack", "kill", "slaughter"], 
        ["look", "see", "glance"], 
        ["take", "pick", "choose"], 
        ["throw", "launch", "toss"], 
        ["equip", "gear", "prepare"],
        ["check", "verify", "study"]
    ]
    
    syn_vbs = []
    for list_vbs in ref_vbs:
        tmp = []
        for syn in list_vbs:
            for words in wordnet.synsets(syn):
               for lemma in words.lemmas():
                    tmp.append(lemma.name())
        syn_vbs.append(tmp)
    syn_items = []
    for item in items:
        tmp = []
        for w in wordnet.synsets(item):
            for lemma in w.lemmas():
                tmp.append(lemma.name())
        syn_items.append(tmp)
    syn_monsters = []
    for monster in monsters:
        tmp = []
        for w in wordnet.synsets(monster):
            for lemma in w.lemmas():
                tmp.append(lemma.name())
        syn_monsters.append(tmp)
                  
        
    # on cherche dans les synonymes des mots de référence
    matched = False
    for i in range(len(syn_vbs)):
        if verb in syn_vbs[i]:
            result.append(ref_vbs[i][0])
            matched = True
            break
    if not matched:
        result.append("ERROR")
    
    # on rajoute les mots restants en argument
    for word, tag in cap.clean_words:
        result.append(word)
    print(result)