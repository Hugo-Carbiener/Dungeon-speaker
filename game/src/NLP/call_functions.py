# récupération de l'entrée utilisateur nettoyée (partie de Solène)
import cleaning_and_parsing as cap
import nltk
from nltk.corpus import wordnet

# on renverra un tableau avec le verbe correspondant à l'action
# ainsi que les éventuels arguments
result = []

items = ["club", "knife", "dagger", "bow", "sword", "axe", "whip", "claymore", "scythe", "katana"]
monsters = ["zombie", "skeleton", "ghost", "undead", "wolf", "spider", "slime", "ghoul", "necromancer", "dragon"]

#on commnence par les verbes
# on considère qu'il n'y a qu'un verbe dans la phrase, on le récupère dans la liste
verb = ""
for i in cap.final_words:
    if 'V' in i[1]:
        verb = i[0]
        cap.final_words.remove(i)
        break

if verb == "":
    print("ERROR")
else:

    # verbes de référence : les premiers de chaque liste
    # ces verbes servent à classifier le verbe donné par l'utilisateur
    # dans une des actions possibles correspondantes

    ref_vbs = [
        ["move", "go", "run"], 
        ["attack", "kill", "slaughter"], 
        ["look", "see", "glance"], 
        ["take", "pick", "choose", "grab", "put"], 
        ["throw", "launch", "toss"], 
        ["equip", "gear", "prepare"],
        ["check", "verify", "study"]
    ]
    
    # construction de la liste de synonymes de verbes
    syn_vbs = []
    for list_vbs in ref_vbs:
        tmp = []
        for syn in list_vbs:
            for words in wordnet.synsets(syn):
               for lemma in words.lemmas():
                    tmp.append(lemma.name())
        syn_vbs.append(tmp)
    # construction de la liste de synonymes des items
    syn_items = []
    for item in items:
        tmp = []
        for w in wordnet.synsets(item):
            for lemma in w.lemmas():
                tmp.append(lemma.name())
        syn_items.append(tmp)
    # construction de la liste de synonymes des monstres
    syn_monsters = []
    for monster in monsters:
        tmp = []
        for w in wordnet.synsets(monster):
            for lemma in w.lemmas():
                tmp.append(lemma.name())
        syn_monsters.append(tmp)
                  
    # on cherche dans les synonymes des mots de référence le mot nettoyé de l'entrée utilisateur
    matched = False
    for i in range(len(syn_vbs)):
        if verb in syn_vbs[i]:
            result.append(ref_vbs[i][0])
            matched = True
            break
    if not matched:
        result.append("ERROR")
    
    # on rajoute les mots restants en argument
    for word, tag in cap.final_words:
        result.append(word)
    print(result)