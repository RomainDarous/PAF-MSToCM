import matplotlib.pyplot as plt
import csv
import numpy as np
import os
from mpl_toolkits import mplot3d
import matplotlib.colors as colors
import argparse


file = open(os.path.join(os.path.dirname(__file__), "data.csv"))
fichier = csv.reader(file, delimiter=',')
new_color = list(colors.cnames.keys())


def init_cmd():
    ap = argparse.ArgumentParser()
    ap.add_argument("-g", "--groupid", default=1, help="LOL")
    args = vars(ap.parse_args())
    return args


args = init_cmd()


def affiche(idgroup):

    res=[]
    X=[]
    Y=[]



    for ligne in fichier:
        res.append(ligne)

    for i in range(1,len(res)):
        if len(res[i]) == len(res[1]):
            if int(res[i][0]) == idgroup :
                X.append(int(res[i][7]))
                Y.append(int(res[i][-1]))


    if len(X) == 1:
        plt.scatter(Y, X)

    else :
        plt.plot(Y,X)

    plt.xlabel("Temps (s)")
    plt.ylabel("Nombre de boid ")


    plt.title("Nombre de boid dans le groupe " + str(idgroup) + " en fonction du temps")
    plt.show()

############### changer le paramètre pour voir un groupe différent ###############

affiche(int(args["groupid"]))

##################################################################################


file.close()
