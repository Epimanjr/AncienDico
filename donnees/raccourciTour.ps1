$shell = New-Object -ComObject WScript.Shell
$Link = $shell.CreateShortcut("C:\Users\blaiseWin\Desktop\MaxDico.lnk")
$Link.TargetPath = "C:\Users\blaiseWin\Documents\NetBeansProjects\NewSuperMDico\NewSuperMDico.jar"
$Link.Hotkey = “CTRL+SHIFT+M”
$Link.IconLocation = "C:\Users\blaiseWin\Documents\NetBeansProjects\NewSuperMDico\src\graphique\iconfrch.ico"
$Link.Description = “Test of Shortcut”
$Link.WorkingDirectory = "C:\Users\blaiseWin\Documents\NetBeansProjects\NewSuperMDico\"
$Link.Save()