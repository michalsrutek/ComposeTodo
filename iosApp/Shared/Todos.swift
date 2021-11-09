//
//  Todos.swift
//  composetodo (iOS)
//
//  Created by Philip Wedemann on 04.11.21.
//

import SwiftUI
import shared

struct Todos: View {
    let viewModel: TodoViewModel
    
    @State private var todos = [Todo]()
    
    var body: some View {
        List(todos) { todo in
            TodoItemRow(item: todo)
        }.refreshable {
            viewModel.refresh()
        }.onReceive(viewModel.todos
                        .publisher([Todo].self)
                        .replaceError(with: [])
        ) { newValues in
            self.todos = newValues
        }
    }
}

struct Todos_Previews: PreviewProvider {
    static var previews: some View {
        Todos(viewModel: TodoViewModel.init(scope: CoroutineScopeKt.MainScope(), repo: TestRepo()))
    }
    
    class TestRepo: TodoRepository {
        init() {
            
        }
    
        func create(title: String, until: Instant?) async throws -> KotlinUnit? {
            KotlinUnit()
        }
        
        func delete(todo: Todo_) async throws -> KotlinUnit? {
            KotlinUnit()
        }
        
        func deleteAll() async throws -> KotlinUnit? {
            KotlinUnit()
        }
        
        func sync() async throws -> KotlinUnit? {
            KotlinUnit()
        }
        
        var todos: Flow {
            get {
                BuildersKt_.emptyFlow()
            }
        }
    }
}