import matplotlib.pyplot as plt
import csv
import numpy as np
import os
from mpl_toolkits import mplot3d
import matplotlib.colors as colors


file = open(os.path.join(os.path.dirname(__file__), "data.csv"))
fichier = csv.reader(file, delimiter=',')
new_color = list(colors.cnames.keys())



def affiche():

    res=[]
    X=[]
    Y=[]
    time =1


    for ligne in fichier:
        res.append(ligne)

    for i in range(1,len(res)):
        if len(res[i]) == len(res[1]):
            if int(res[i][-1]) == time :
                X.append(int(res[i][8]))
                Y.append(int(res[i][-1]))
                time += 1



    plt.plot(Y,X)
    plt.xlabel("Temps (s)")
    plt.ylabel("Nombre de groupes")

    plt.title("Nombre de groupes en fonction du temps")
    plt.show()

affiche()
