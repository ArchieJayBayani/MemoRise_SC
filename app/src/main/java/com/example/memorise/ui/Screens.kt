package com.example.memorise.ui

sealed class Screens(val route: String) {
    object MainScreen : Screens(route="main_screen")
    object SettingScreen : Screens(route="setting_screen")
    object AboutUsScreen : Screens(route="AboutUs_screen")
    object NoteSelectScreen : Screens(route="NoteSelect_Screen")
    object BasicNoteScreen : Screens(route="BasicNote_screen")
    object CornellNoteScreen : Screens(route="CornellNote_screen")
    object ChartingNoteScreen : Screens(route="ChartingNote_screen")
    object OutlineNoteScreen : Screens(route="OutlineNote_screen")
    object QuadrantNoteScreen : Screens(route="QuadrantNote_screen")
    object NoteSelectionScreen : Screens(route="NoteSelection_Screen")
    object LadderNoteScreen : Screens(route="LadderNote_Screen")


}



