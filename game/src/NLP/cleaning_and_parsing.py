
import nltk
from nltk.corpus import stopwords	#permet d'enlever les mots inutiles
from nltk import word_tokenize	#permet de tokeniser par mot
from nltk import WordNetLemmatizer
import sys
##interface entree : STRING##



# recuperer l'entree utilisateur depuis un fichier texte
# Ouvrir et lire le fichier 
#text_file = open("test_00.txt")	#mettre nom_fichier.txt entre les guillemets
#text = text_file.read()

text = ""
for i in range(len(sys.argv) - 1):
    text += sys.argv[i + 1] + " "


#text = input("Entrez votre action : ")


#print('type of text : ')
#print(type(text))
#print("\n")



#premiere fonction : word tokenization
words = word_tokenize(text)

# print('words :')
# print(words)
# print("\n")

#deuxieme fonction : enlever la ponctuation
#rmq : on le fait avant de tagger les mots car la fonction isalpha ne prend pas de tuples en entree
word_no_punc = []
for w in words:
    if w.isalpha():
        word_no_punc.append(w.lower())

# print("word_no_punc :")
# print(word_no_punc)
# print("\n")

#troisieme fonction : tagger les mots
tagged_words = []

for w in word_no_punc:
    tagged_words = nltk.pos_tag(word_no_punc)

# print('tagged words :')
# print(tagged_words)
# print("\n")

#quatrieme fonction : clean la liste
clean_words = []

stopwords = stopwords.words("english")

for word in tagged_words:
    if word[0] not in stopwords:
        clean_words.append(word)


print(clean_words)

final_words = []
lemma = WordNetLemmatizer()
for word in clean_words:
    if 'V' in word[1]:
        final_words.append((lemma.lemmatize(word[0], pos="v"), word[1]))
    elif 'N' in word[1]:
        final_words.append((lemma.lemmatize(word[0], pos="n"), word[1]))
    else:
        final_words.append(word)
print(final_words)