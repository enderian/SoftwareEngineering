###### Παραδοτέο R2

# Εισαγωγή
Στο παρών έγγραφο θα αναλυθούν όλες οι λεπτομέρεις που αφορούν την εφαρμογή διαφείρισης εργαστηρίων CSLabs. Συγκεκριμένα θα γίνει λεπτομερής ανάλυση των περιπτώσεων χρήσης καθώς και του και του μοντέλου πεδίου του συστήματός μας.

## Εμβέλεια

Το λογισμικό υλοποιεί τις λειτουργίες της γενικότερης επίβλεψης και διαχείρισης των CSLabs. Πιο συγκεκριμένα η εφαρμογή προσφέρει:

- Προβολή των κατόψεων των εργαστηρίων από απλούς χρήστες.
- Προβολή του προγράμματος των εργαστηρίων.
- Προβολή εμπλουτισμένων κατόψεων από διαχειριστές με παραπάνω στοιχεία για κάθε μηχάνημα, όπως πληροφορίες για την τρέχουσα συνεδρία και τον τρέχον χρήστη.
- Διαχείριση της λειτουργείας των μηχανημάτων από διαχειριστές, όπως επανεκκίνηση, αναστολή λειτουργίες και αποσύνδεση τρέχοντα χρήστη.
- Προβολή ιστορικών δεδομένων από διαχειριστές, όπως πληροφορίες για τον εκάστοτε χρήστη και τις συνεδρίες του στα εργαστήρια.
- Προβολή, επεξεργασία, καθώς και δημιουργία και χρήση πρωτοτύπων από διαχειριστές όσον αφορά τα χαρακτηριστικά κάθε μηχανήματος  (όπως επεξεργαστής, κάρτα γραφικών, κλπ.)

Η εφαρμογή δεν θα προσφέρει τα παρακάτω:

- Λειτουργίες απομακρυσμένου ελέγχου.
- Επίβλεψη των διεργασιών.
- Παρακολούθηση της δραστηριότητας των χρηστών του εργαστηρίου.

## Ορισμοί, ακρώνυμα & συντομογραφίες

| Όρος | Επεξήγηση |
|:----:|:---------:|
| CSLab | Computer Science Lab(oratory) - Εργαστήριο Πληροφορικής |
| Διαχειριστής | Ο διαχειριστής είναι ένα άτομο ορισμένο από το προσωπικό του εργαστηρίου το οποίο είναι υπεύθυνο για την ορθή λειτουργία των εργαστηρίων και των μηχανημάτων τους. |
| Χρήστης | Ως χρήστη ονομάζουμε οποιοδήποτε άτομο έχει δικάιωμα χρήσης των εργαστηρίων και των πόρων του. |
| Image | Ως image ορίζουμε την έκδοση του συνόλου των προγραμμάτων και του λειτουργικού συστήματος που είναι εγκατεστημένα σε έναν υπολογιστή. |

## Επισκόπηση

Παρακάτω θα αναλυθεί το διάγραμμα περιπτώσεων χρήσης του παραδοτέου R1 σε πίνακα και θα αναλυθούν όλες οι περαιτέρω περιπτώσεις χρήσης. Επίσης θα αναπαρασταθούν σε πίνακα οι περιγραφές των Actors όπως και οι ανάγκες τους. Ακόμα θα αναλυθούν οι συμπληρωματικές προδιαγραφές του συστήματος. Τέλος θα γίνει μια σύντομη περιγραφή των επιχειρησιακών κανόνων και ποιο σημαντικά το υποστηρικτικό υλικό και το βασικό μοντέλο πεδίου.

Στη συνέχεια, θα αναλύσουμε πλήρως το λογισμικό, τους διάφορους χρήστες, τις διεπαφές, τις κλάσσεις του, την οργάνωσή του και άλλα.

# Συνολική Περιγραφή

## Επισκόπηση μοντέλου περιπτώσεων χρήσης

![](deliverables/R1/use_case_r1.png)

| Περίπτωση Χρήσης | Περιγραφή |
|:----------------:|:---------:|
| ΠΧ1: Αυθεντικοποίηση χρήστη | Η περίπτωση χρήσης για την εξουσιοδότηση σύνδεσης του Χρήστη στην εφαρμογή. |
| ΠΧ2: Προβολή κατάστασης υπολογιστών | Η περίπτωση χρήσης για την προβολή κατάστασης των μηχανημάτων του εργηαστηρίου. |
| ΠΧ3: Προβολή προγράμματος εργαστηρίων | Η περίπτωση χρήσης για την προβολή του τρέχοντος προγράμματος που έχει ανατεθεί στο εργαστήριο. |
| ΠΧ4: Προβολή κάτοψης εργαστηρίου | Η περίπτωση χρήσης για την προβολή της πραγματικής αναπαράστασης των θέσεων εργασίας μέσα στο εργαστήριο. |
| ΠΧ5: Απομακρυσμένος έλεγχος υπολογιστών | Η περίπτωση χρήσης για τον απομακρυσμένο έλεγχο των υπολογιστών του εργαστηρίου. |
| ΠΧ6: Προβολή ιστορικού χρήσεως | Η περίπτωση χρήσης για την προβολή του ιστορικού χρήσης των μηχανημάτων και των χρηστών. |
| ΠΧ7: Καταχώρηση νέων μηχανημάτων | Η περίπτωση χρήσης για την προσθήκη ενός μηχανήματος στην κάτοψη του εργαστηρίου. |
| ΠΧ8: Δημιουργία και χρήση πρότυπων νέων μηχανημάτων | Η περίπτωση χρήσης για την δημιουργία ενός προτύπου υπολογιστή (hardware & image wise) και η προσθήκη του στο σύστημα. |


Όλες οι περιγραφές των περιπτώσεων χρήσης παρέχονται στην υποενότητα περιγραφές περιπτώσεων χρήσης.


## Υποθέσεις & εξαρτήσεις

# Ειδικές Απαιτήσεις

## Περιπτώσεις χρήσης

### Οι ενδιαφερόμενοι & οι ανάγκες τους

### Actors του συστήματος

### Περιγραφές περιπτώσεων χρήσης

## Συμπληρωματικές προδιαγραφές

### Απαιτήσεις διεπαφών

#### Διεπαφές χρήστη

#### Διεπαφες υλικού

#### Διεπαφές επικοινωνίας

#### Διεπαφές λογισμικού

### Περιορισμοί σχεδίασης & υλοποίησης

### Ποιοτικά χαρακτηριστικά

#### Απόδοση

#### Διαθεσιμότητα

#### Ασφάλεια

#### Ευελιξία

#### Ευχρηστία

# Υποστηρικτικό Υλικό

## Μοντέλο πεδίου

## Ανάλυση περιπτώσεων χρήσης
----------------

### Κλάσεις ανάλυσης

### Συμπεριφορές

## Άλλα μοντέλα
----------------

## Επιχειριακοί κανόνες