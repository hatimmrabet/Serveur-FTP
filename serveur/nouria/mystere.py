class Personne:
    def __init__(self,nom,age):
        self.nom = nom
        self.age = age

def mystere(dict):
    print("Bienvenue sur Escape Game du FIL")
    ret = ""
    for elem in dict.keys():
        ret += dict[elem].nom[2] + str(dict[elem].age)[-2%2]
        if len(elem) > 2:
            ret += elem[-1]
    return ret.upper()

dictionnaire  = {
    "AL" : Personne("Khalid",59),
    "L10" : Personne("Albert",2),
}

print(mystere(dictionnaire))