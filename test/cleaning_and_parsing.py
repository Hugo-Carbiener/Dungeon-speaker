import nltk
from nltk.corpus import stopwords	#permet d'enlever les mots inutiles
from nltk import word_tokenize	#permet de tokeniser par mot
from nltk import WordNetLemmatizer

##interface entree : STRING##



# recuperer l'entree utilisateur depuis un fichier texte
# Ouvrir et lire le fichier 
#text_file = open("test_00.txt")	#mettre nom_fichier.txt entre les guillemets
#text = text_file.read()

text = input("Entrez votre action : ")

print('type of text : ')
print(type(text))
print("\n")


#premiere fonction : word tokenization
words = word_tokenize(text)

print('words :')
print(words)
print("\n")

#deuxieme fonction : enlever la ponctuation
words_no_punc = []

for word in words:
    if word.isalpha():
        words_no_punc.append(word.lower())

print('words_no_punc :')
print(words_no_punc)
print("\n")

#troisieme fonction : enlever les mots inutiles
clean_words = []

#liste de stopwords
stopwords = stopwords.words("english")

for word in words_no_punc:
    if word not in stopwords:
        clean_words.append(word)

print('clean_words :')
print(clean_words)
print("\n")


#quatrieme fonction : separer les mots en fonction de leur nature
tagged_words = []

for word in clean_words:
    tagged_words = nltk.pos_tag(clean_words)

#remarque : JJ = adjectifs, NN = noms singuliers, VBP = verbes au present, pas a la troisieme personne du singulier
print('tagged words :')
print(tagged_words)
print("\n")












