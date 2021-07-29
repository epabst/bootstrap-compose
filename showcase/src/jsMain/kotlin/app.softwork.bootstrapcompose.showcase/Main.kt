package app.softwork.bootstrapcompose.showcase

import androidx.compose.runtime.*
import app.softwork.bootstrapcompose.*
import org.jetbrains.compose.web.*
import org.jetbrains.compose.web.dom.*

data class AppState(val activePage: ActivePage)

fun main() {
    renderComposable(rootElementId = "root") {
        var state by remember { mutableStateOf(AppState(ActivePage.Box)) }

        Navbar(
            placement = NavbarPlacement.StickyTop,
            collapseBehavior = NavbarCollapseBehavior.AtBreakpoint(Breakpoint.Large),
            colorScheme = Color.Dark,
            toggler = true,
            togglerPosition = TogglerPosition.Right,
            brand = {
                Brand {
                    Text("bootstrap-compose Showcase")
                }
            },
            navAttrs = { classes("flex-grow-1") }
        ) {
            NavbarDropDown(title = state.activePage.displayName, href = "#") {
                ActivePage.values().forEach {
                    Button(it.displayName) {
                        state = state.copy(activePage = it)
                    }
                }
            }
            NavbarDropDown(title = "Login", href = "#") {
                Custom {
                    RawInputView()
                }
            }
            A(href = "https://github.com/hfhbd/bootstrap-compose", attrs = {
                classes("nav-link", "ms-auto", "link-secondary")
            }) {
                Text("View on GitHub ")
                Icon("github")
            }
        }

        Main {
            when (state.activePage) {
                ActivePage.Box -> BoxView()
                ActivePage.ChecksAndRadios -> ChecksAndRadiosView()
                ActivePage.Form -> FormView()
                ActivePage.Grid -> GridBoxView()
                ActivePage.InputGroup -> InputGroupView()
                ActivePage.ListGroup -> ListGroupView()
                ActivePage.Modal -> ModalView()
                ActivePage.Navbar -> NavbarView()
                ActivePage.Range -> RangeView()
                ActivePage.Select -> SelectView()
                ActivePage.Table -> TableView()
            }
        }
        Footer(attrs = { classes("footer", "mt-auto") }) {
            Container {
                Hr()
                P {
                    Text("Some Footer")
                }
            }
        }
    }
}

enum class ActivePage(val displayName: String) {
    Box("Boxes"),
    ChecksAndRadios("Checks & radios"),
    Form("Forms"),
    Grid("Grid Layout"),
    InputGroup("Input group"),
    ListGroup("Lists"),
    Modal("Modal"),
    Navbar("Navbars"),
    Range("Range"),
    Select("Select"),
    Table("Table")
}

