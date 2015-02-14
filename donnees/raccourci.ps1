$shell = New-Object -ComObject WScript.Shell
$Link = $shell.CreateShortcut("C:\Users\Maxime\Desktop\MaxDico.lnk")
$Link.TargetPath = "C:\Users\Maxime\Documents\NetBeansProjects\NewSuperMDico\NewSuperMDico.jar"
$Link.Hotkey = “CTRL+SHIFT+N”
$Link.IconLocation = "C:\Users\Maxime\Documents\NetBeansProjects\NewSuperMDico\src\graphique\iconfrch.ico"
$Link.Description = “Test of Shortcut”
$Link.WorkingDirectory = "C:\Users\Maxime\Documents\NetBeansProjects\NewSuperMDico\"
$Link.Save()