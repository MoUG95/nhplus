# NHPlus

## Benutzername + Passwort

Es gibt bisher zwei User mit unterschiedlichen Berechtigungen:

Benutzer: 0, Passwort: 1111 (Quasi Administrator, kann alle anderen User sehen und neue User anlegen)

Benutzer: 2, Passwort 2222 (User eines Pflegers, kann die User-Übersicht nicht sehen)



## Features und Testfälle

| Funktionalität                                           | Testfall                                                                                                                     | Status                                                                                                                                                             |
|:--------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Login Implementierung                                    | TF1_: Einloggen                                                                                                              | Funktioniert                                                                                                                                                       |
|                                                          | TF2_: Operationen ausführen, die nur bestimmte Berechtigungen benötigen.                                                     | Zwei Berechtigungsstufen existieren. Stufe 1 kann User sehen und anlegen, Stufe 2 nicht.                                                                           |
|                                                          | TF3_: Benutzerverwaltung für Admin-Nutzer.                                                                                   | Funktioniert                                                                                                                                                       |
|                                                          | TF4_: Ausloggen.                                                                                                             | Funktioniert                                                                                                                                                       |
| Entfernung Vermögensstand                                | TF1_: Vermögensstand wird nicht mehr in Patientenansicht aufgeführt und beim Anlegen neuer Patienten nicht mehr nachgefragt. | Vermögensstand wird nicht mehr abgefragt und wurde aus der Datenbank entnommen.                                                                                    |
| Pfleger-Modul Implementierung                            | TF1_: „Pfleger/innen“-Button anzeigen.                                                                                       | Funktioniert                                                                                                                                                       |
|                                                          | TF2_: Übersicht aller Pfleger.                                                                                               | Funktioniert                                                                                                                                                       |
|                                                          | TF3_: Pfleger/innen können hinzugefügt / gelöscht werden.                                                                    | Funktioniert                                                                                                                                                       |
|                                                          | TF4_: In der Behandlungsansicht werden die jeweiligen Pfleger mit angezeigt.                                                 | Funktioniert                                                                                                                                                       |
|                                                          | TF5_: Beim Anlegen einer neuen Behandlung kann der ausführende Pfleger mittels ComboBox ausgewählt werden.                   | Funktioniert                                                                                                                                                       |
| Behandlungsende markieren                                | TF1_: Behandlungsende bei Patient kann markiert werden.                                                                      | Funktioniert                                                                                                                                                       |
| Automatische Sperrung der Daten nach Behandlungsende     | TF1_: Patientendaten werden nach Sperrung nicht mehr angezeigt.                                                              | Funktioniert                                                                                                                                                       |
| Automatische Datenlöschung 30 Jahre nach Behandlungsende | TF1_: Gesperrte Daten werden nach 30 Jahren automatisch gelöscht.                                                            | Funktioniert                                                                                                                                                       |
|                                                          | TF2_: Die Löschung beeinträchtigt nicht die Performance.                                                                     | Schwierig zu berurteilen, da der Job aber nur einmal am Tag ausgeführt wird und die Datenbank des Pflegeheims recht übersichtlich ist sagen wir mal: Funktioniert. |
| Verschlüsselung der Passwörter                           | TF1_: Passwörter sind in der Datenbank gehasht zu finden                                                                     | Funktioniert                                                                                                                                                       |

## Zusätzliche Features

| Funktionalität                 | Testfall                                                 | Status       |
|:------------------------------:|:--------------------------------------------------------:|:------------ |
| Verschlüsselung der Passwörter | TF1_: Passwörter sind in der Datenbank gehasht zu finden | Funktioniert |



## Informationen zur Lernsituation

Du bist Mitarbeiter der HiTec GmbH, die seit über 15 Jahren IT-Dienstleister und seit einigen Jahren ISO/IEC 27001 zertifiziert ist. Die HiTec GmbH ist ein mittelgroßes IT-Systemhaus und ist auf dem IT-Markt mit folgenden Dienstleistungen und Produkten vetreten: 

Entwicklung: Erstellung eigener Softwareprodukte

Consulting: Anwenderberatung und Schulungen zu neuen IT- und Kommunikationstechnologien , Applikationen und IT-Sicherheit

IT-Systembereich: Lieferung und Verkauf einzelner IT-Komponenten bis zur Planung und Installation komplexer Netzwerke und Dienste

Support und Wartung: Betreuung von einfachen und vernetzten IT-Systemen (Hard- und Software)

Für jede Dienstleistung gibt es Abteilungen mit spezialisierten Mitarbeitern. Jede Abteilung hat einen Abteilungs- bzw. Projektleiter, der wiederum eng mit den anderen Abteilungsleitern zusammenarbeitet.

## Projektumfeld und Projektdefinition

Du arbeitest als Softwareentwickler in der Entwicklungsabteilung. Aktuell bist du dem Team zugeordnet, das das Projekt "NHPlus" betreut. Dessen Auftraggeber - das Betreuungs- und Pflegeheim "Curanum Schwachhausen" - ist ein Pflegeheim im Bremer Stadteil Schwachhausen - bietet seinen in eigenen Zimmern untergebrachten Bewohnern umfangreiche Therapie- und Serviceleistungen an, damit diese so lange wie möglich selbstbestimmt und unabhängig im Pflegeheim wohnen können. Curanum Schwachhausen hat bei der HiTec GmbH eine Individualsoftware zur Verwaltung der Patienten und den an ihnen durchgeführten Behandlungen in Auftrag gegeben. Aktuell werden die Behandlungen direkt nach ihrer Durchführung durch die entsprechende Pflegekraft handschriftlich auf einem Vordruck erfasst und in einem Monatsordner abgelegt. Diese Vorgehensweise führt dazu, dass Auswertungen wie z.B. welche Behandlungen ein Patient erhalten oder welche Pflegkraft eine bestimmte Behandlung durchgeführt hat, einen hohen Arbeitsaufwand nach sich ziehen. Durch NHPlus soll die Verwaltung der Patienten und ihrer Behandlungen elektronisch abgebildet und auf diese Weise vereinfacht werden.

Bei den bisher stattgefundenen Meetings mit dem Kunden konnten folgende Anforderungen an NHPlus identifiziert werden:

- alle Patienten sollen mit ihrem vollen Namen, Geburtstag, Pflegegrad, dem Raum, in dem sie im Heim untergebracht sind, sowie ihrem Vermögensstand erfasst werden.

- Die Pflegekräfte werden mit ihrem vollen Namen und ihrer Telefonnumer erfasst, um sie auf Station schnell erreichen zu können.

- jede Pflegekraft erfasst eine Behandlung elektronisch, indem sie den Patienten, das Datum, den Beginn, das Ende, die Behandlungsart sowie einen längeren Text zur Behandlung erfasst.

- Die Software muss den Anforderungen des Datenschutzes entsprechen. 

- Die Software ist zunächst als Desktopanwendung zu entwickeln, da die Pflegekräfte ihre Behandlungen an einem stationären Rechner in ihrem Aufenthaltsraum erfassen sollen.

Da in der Entwicklungsabteilung der HiTech GmbH agile Vorgehensweisen vorgeschrieben sind, wurde für NHPlus Scum als Vorgehensweise gewählt.

## Stand des Projektes

In den bisherigen Sprints wurden die Module zur Erfassung der Patienten- und Behandlungsdaten fertiggestellt. Es fehlt das Modul zur Erfassung der Pflegekräfte. Deswegen kann bisher ebenfalls nicht erfasst werden, welche Pflegekraft eine bestimmte Behandlung durchgeführt hat. In der letzten Sprint Review sind von der Curanum Schwachhausen Zweifel angebracht worden, dass die bisher entwickelte Software den Anforderungen des Datenschutzes genügt.

## Technische Hinweise

Wird das Open JDK verwendet, werden JavaFX-Abhängigkeiten nicht importiert. Die Lösung besteht in der Installation der neuesten JDK-Version der Firma Oracle.

## Technische Hinweise zur Datenbank

- Benutzername: SA
- Passwort: SA
- Bitte nicht in die Datenbank schauen, während die Applikation läuft. Das sorgt leider für einen Lock, der erst wieder verschwindet, wenn IntelliJ neugestartet wird!
